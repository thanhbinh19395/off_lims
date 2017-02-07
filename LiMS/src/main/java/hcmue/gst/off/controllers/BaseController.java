package hcmue.gst.off.controllers;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
public class BaseController {
    protected String View(String viewName) {
        return this.getClass().getSimpleName()+"/"+viewName;
    }
}
