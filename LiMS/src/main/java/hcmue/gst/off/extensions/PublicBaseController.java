package hcmue.gst.off.extensions;

/**
 * Created by Ho Phuong on 20/02/2017.
 */
public class PublicBaseController extends BaseController {
    @Override
    protected String View(String viewName) {
        return "/Public/" + super.View(viewName);
    }

}
