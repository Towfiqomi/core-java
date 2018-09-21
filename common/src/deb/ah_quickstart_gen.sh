#!/bin/sh -e

. /usr/share/debconf/confmodule
. /usr/share/arrowhead/conf/ahconf.sh

db_input critical arrowhead-common/public_mysql || true
db_go || true
db_get arrowhead-common/public_mysql

case ${RET} in
    Yes )
        if [ $(mysql -u root -sse "SELECT EXISTS(SELECT 1 FROM mysql.user WHERE user = 'arrowhead' AND host = '%')") != 1 ]; then
            mysql -e "CREATE USER arrowhead@'%' IDENTIFIED BY '${AH_PASS_DB}';"
            mysql -e "GRANT ALL PRIVILEGES ON arrowhead.* TO arrowhead@'%';"
            mysql -e "FLUSH PRIVILEGES;"
        fi
        sed -i 's/^\(bind-address[ \t]*=[ \t]*\).*$/\10.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf
        systemctl restart mysql
        ;;
    No )
        ;;
esac

ahcert ~/ SecureTemperatureSensor
ahcert ~/ client1
ahcert_export authorization ~/
chown $(logname) ~/*.crt ~/*.p12

# Get public base64 cert for database!!!
consumer64pub=$(\
    sudo keytool -exportcert -rfc -keystore ~/client1.p12 -storepass ${AH_PASS_CERT} -v -alias client1 \
    | openssl x509 -pubkey -noout \
    | sed '1d;$d' \
    | tr -d '\n'\
)
provider64pub=$(\
    sudo keytool -exportcert -rfc -keystore ~/SecureTemperatureSensor.p12 -storepass ${AH_PASS_CERT} -v -alias SecureTemperatureSensor \
    | openssl x509 -pubkey -noout \
    | sed '1d;$d' \
    | tr -d '\n'\
)

# TODO Autogenerated ids
mysql -u root arrowhead <<EOF
LOCK TABLES arrowhead_system WRITE, arrowhead_service WRITE, intra_cloud_authorization WRITE, orchestration_store WRITE;

INSERT INTO arrowhead_system VALUES (1, 'localhost', '${consumer64pub}', 'client1');
INSERT INTO arrowhead_system VALUES (2, 'localhost', '${provider64pub}', 'SecureTemperatureSensor');
INSERT INTO arrowhead_service VALUES (1, 'IndoorTemperature');
INSERT INTO intra_cloud_authorization VALUES (1, 1, 2, 1);
INSERT INTO orchestration_store VALUES (1, 'Y', NULL, NOW(), 'Test', 1, 1, NULL, 2, 1);

UNLOCK TABLES;
EOF

echo
echo "Password for certificate stores: ${AH_PASS_CERT}" >&2
