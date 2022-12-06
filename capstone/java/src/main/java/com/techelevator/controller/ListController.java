package com.techelevator.controller;

import com.techelevator.dao.ListDao;
import com.techelevator.model.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/group")
@PreAuthorize("isAuthenticated()")
public class ListController {
    private ListDao listDao;

    @GetMapping(path = "/{groupId}/{listId}")
    public List getListByListId(@PathVariable("groupId") int groupId, @PathVariable("listId") int listId, @RequestBody @Valid int userId, Principal principal) {
        return listDao.getList(groupId, listId, userId);
    }

    @PostMapping(path = "/{groupId}")
    public boolean createAList(@PathVariable("groupId") int groupId, @RequestBody @Valid int userId, Principal principal) {
        return listDao.createList(groupId, userId);
    }

    @PutMapping(path = "/updateList")
    public boolean updateAList(@RequestBody @Valid List list) {
        return listDao.updateList(list);
    }

    @DeleteMapping(path = "/{groupId}")
    public boolean deleteAList(@PathVariable("groupId") int groupId, @RequestBody @Valid int userId, Principal principal) {
        return listDao.deleteList(groupId, userId);
    }
}
