package hcmue.gst.off.extensions;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class AdminControllerBase extends ControllerBase {

    @Override
    protected String View(String viewName) {
        return "/Admin/" + super.View(viewName);
    }
}
