package hcmue.gst.off.services;

import hcmue.gst.off.entities.User;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
public interface SecurityService {
    String findLoggedInUsername();
    User getUser();
}
