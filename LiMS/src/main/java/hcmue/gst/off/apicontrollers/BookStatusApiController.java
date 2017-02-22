package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.entities.Role;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RoleRepository;
import hcmue.gst.off.services.BookStatusService;
import hcmue.gst.off.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited: by dylan on 16/02/2017
 */
@RestController
@RequestMapping("/api/BookStatus")
public class BookStatusApiController {


    @Autowired
    private BookStatusService bookStatusService;

    @RequestMapping("/Save")
    Result<BookStatus> Save(BookStatus model){
        return bookStatusService.save(model);
    }
    @RequestMapping("/Deletes")
    Result Deletes(Long id){
        return bookStatusService.delete(id);
    }
    @RequestMapping("/GetList")
    PageableResult<BookStatus> GetList(BookStatus model, Pageable p){
        return bookStatusService.search(model,p);
    }
    @RequestMapping("/FindByNameContaining")
    Result GetListByName(String description)
    {
        return bookStatusService.findByDescriptionContaining(description);
    }
}
