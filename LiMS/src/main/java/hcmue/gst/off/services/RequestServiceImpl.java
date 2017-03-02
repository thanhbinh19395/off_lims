package hcmue.gst.off.services;

import hcmue.gst.off.entities.CommonStatus;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited by dylan on 18/02/2017.
 */
@Service
public class RequestServiceImpl  extends BaseCommand implements RequestService {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Result<Iterable<Request>> findAll() {
        return Success(requestRepository.findAll());
    }

    @Override
    public Result<Request> findOne(Long id) {
        return Success(requestRepository.findOne(id));
    }


    @Override
    public Result<Request> save(Request request) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (request.getId() == null) {
            request.setCreated_by(user);
            request.setCreated_date(new Date());
            request.setStatus(CommonStatus.PENDING.getValue());
        }
        else {
            request.setUpdate_date(new Date());
            request.setUpdate_by(user);
        }
        return Success(requestRepository.save(request), "Lưu thành công");
    }

    @Override
    public Result delete(Long id) {
        requestRepository.delete(id);
        return Success(id,"Xóa thành công");
    }

    @Override
    public Result findByStatus(String status) {
        return Success(requestRepository.findByStatusContaining(status));
    }

    @Override
    public PageableResult<Request> search(Request model, Pageable p) {
        return Success(requestRepository.search(model,new PageRequest(p.getPageNumber(),PAGESIZE,p.getSort())));
    }

    @Override
    public Result<Iterable<Request>> search(Request model) {
        return Success(requestRepository.search(model));
    }
}
