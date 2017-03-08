package hcmue.gst.off.business;

import hcmue.gst.off.entities.CommonStatus;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ho Phuong on 07/03/2017.
 */
@Component
public class CancelBookRequestBusiness extends BaseCommand {
    private Long id;

    @Autowired
    private RequestService requestService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result Execute() {
        //Modify request status to CANCELLED
        Request request = requestService.findOne(id).getData();
        request.setStatus(CommonStatus.CANCELLED);
        requestService.save(request);

        return Success();
    }
}
