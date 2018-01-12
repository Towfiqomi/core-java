package eu.arrowhead.common.json.supportadapter;

import eu.arrowhead.common.database.ArrowheadCloud;
import eu.arrowhead.common.database.ArrowheadSystem;
import eu.arrowhead.common.messages.PreferredProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class ServiceRequestFormSupport {

  private ArrowheadSystem requesterSystem;
  private ArrowheadCloud requesterCloud;
  private ArrowheadServiceSupport requestedService;
  @XmlJavaTypeAdapter(BooleanMapAdapter.class)
  private Map<String, Boolean> orchestrationFlags = new HashMap<>();
  private List<PreferredProvider> preferredProviders = new ArrayList<>();

  public ServiceRequestFormSupport() {
  }

  public ServiceRequestFormSupport(ArrowheadSystem requesterSystem, ArrowheadCloud requesterCloud, ArrowheadServiceSupport requestedService,
                                   Map<String, Boolean> orchestrationFlags, List<PreferredProvider> preferredProviders) {
    this.requesterSystem = requesterSystem;
    this.requesterCloud = requesterCloud;
    this.requestedService = requestedService;
    this.orchestrationFlags = orchestrationFlags;
    this.preferredProviders = preferredProviders;
  }

  public ArrowheadSystem getRequesterSystem() {
    return requesterSystem;
  }

  public void setRequesterSystem(ArrowheadSystem requesterSystem) {
    this.requesterSystem = requesterSystem;
  }

  public ArrowheadCloud getRequesterCloud() {
    return requesterCloud;
  }

  public void setRequesterCloud(ArrowheadCloud requesterCloud) {
    this.requesterCloud = requesterCloud;
  }

  public ArrowheadServiceSupport getRequestedService() {
    return requestedService;
  }

  public void setRequestedService(ArrowheadServiceSupport requestedService) {
    this.requestedService = requestedService;
  }

  public Map<String, Boolean> getOrchestrationFlags() {
    return orchestrationFlags;
  }

  public void setOrchestrationFlags(Map<String, Boolean> orchestrationFlags) {
    this.orchestrationFlags = orchestrationFlags;
  }

  public List<PreferredProvider> getPreferredProviders() {
    return preferredProviders;
  }

  public void setPreferredProviders(List<PreferredProvider> preferredProviders) {
    this.preferredProviders = preferredProviders;
  }
}
