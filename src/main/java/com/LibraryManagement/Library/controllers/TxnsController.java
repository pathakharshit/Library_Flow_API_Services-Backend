package com.LibraryManagement.Library.controllers;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.constraint.TxnFilter;
import com.LibraryManagement.Library.dto.request.TxnsRequestDTO;
import com.LibraryManagement.Library.model.Txns;
import com.LibraryManagement.Library.services.TxnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/txn")
public class TxnsController {
    @Autowired
    private TxnsService txnsService;

    @PostMapping("/issue")
    public ResponseEntity<String> createTxns(@RequestBody TxnsRequestDTO txnsRequestDTO) {
        return txnsService.createTxns(txnsRequestDTO);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody TxnsRequestDTO txnsRequestDTO) {
        return txnsService.returnBook(txnsRequestDTO);
    }

    @GetMapping("/filter")
    public List<Txns> filterTxns(@RequestParam String filterBy, @RequestParam FilterOperator operator,
                                 @RequestParam String value) {
        return txnsService.filterTxns(TxnFilter.fromString(filterBy),operator,value);
    }

    @GetMapping
    public List<Txns> getALlTxns() {
        return txnsService.getAllTxns();
    }

    @PostMapping("updateFine")
    public ResponseEntity<String> updateFine(@RequestParam Integer fine) {
        return txnsService.setFine(fine);
    }
}
