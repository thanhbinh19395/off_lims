package hcmue.gst.off.extensions;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
public class BaseController {
    protected String View(){
        return  View(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    protected String View(String viewName){
        return this.getClass().getSimpleName().replace("Controller","") + "/" + viewName;
    }
    protected String View(String viewName, String folderName) {
        return "/" + folderName + "/" + viewName;
    }

}
