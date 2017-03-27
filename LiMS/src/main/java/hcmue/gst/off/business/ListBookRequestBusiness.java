package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RequestRepository;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ho Phuong on 05/03/2017.
 */
@Component
public class ListBookRequestBusiness extends BaseCommand {
    List<Request> requestList;
    Page<Request> requestListPageable;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private SecurityService securityService;

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public Page<Request> getRequestListPageable() {
        return requestListPageable;
    }

    public void setRequestListPageable(Page<Request> requestListPageable) {
        this.requestListPageable = requestListPageable;
    }

    public Result Execute() {
        requestList = new ArrayList<>();
        User user = securityService.getUser();
        Request requestSearchModel = new Request();
        requestSearchModel.setCreated_by(user);
        for (Request request : requestRepository.search(requestSearchModel)) {
            requestList.add(request);
        }
        return Success();
    }

    public Result ExecutePageable(Pageable pageable) {
        User user = securityService.getUser();
        Request requestSearchModel = new Request();
        requestSearchModel.setCreated_by(user);
        requestListPageable = requestRepository.search(requestSearchModel ,pageable);
        return Success(requestListPageable);
    }


}
