package org.openmrs.module.bahmniemrapi.visitLocation;


import org.openmrs.Location;
import org.openmrs.LocationTag;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BahmniVisitLocationServiceImpl implements BahmniVisitLocationService {

    public static final String VISIT_LOCATION = "Visit Location";

    @Override
    public String getVisitLocationForLoginLocation(String loginLocationUuid) {
        Location location = Context.getLocationService().getLocationByUuid(loginLocationUuid);
        while (location != null) {
            if (location.hasTag(VISIT_LOCATION)) {
                return location.getUuid();
            }
            if(location.getParentLocation() == null) return location.getUuid();
            location = location.getParentLocation();
        }
        return null;
    }
}