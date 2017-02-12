package hcmue.gst.off.extensions;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class UserController extends ControllerBase {
    @Override
    protected String View() {
        return "/user" + super.View();
    }
}
