package com.msa.rental.framework.web;

import com.msa.rental.application.usecase.ClearOverdueItemUsecase;
import com.msa.rental.application.usecase.CreateRentalCardUsecase;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.application.usecase.OverdueItemUsecase;
import com.msa.rental.application.usecase.RentItemUsecase;
import com.msa.rental.application.usecase.ReturnItemUsecase;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "대여 API")
public class RentalController {
    private final RentItemUsecase rentItemUsecase;
    private final ReturnItemUsecase returnItemUsecase;
    private final CreateRentalCardUsecase createRentalCardUsecase;
    private final OverdueItemUsecase overdueItemUsecase;
    private final ClearOverdueItemUsecase clearOverdueItemUsecase;
    private final InquiryUsecase inquiryUsecase;


    @ApiOperation(value = "도서카드 생성", notes = "사용자 정보 -> 도서카드 정보")
    @PostMapping("/RentalCard")
    public ResponseEntity<RentalCardOutputDTO> createRentalCard(@RequestBody UserInputDTO userInputDTO) {
        RentalCardOutputDTO createRentalCard = createRentalCardUsecase.createRental(userInputDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createRentalCard);
    }

    @ApiOperation(value = "도서카드 조회", notes = "사용자 정보(id) -> 도서카드 정보")
    @GetMapping("/RentalCard/{userId}")
    public ResponseEntity<RentalCardOutputDTO> getRentalCard(@PathVariable String userId) {
        RentalCardOutputDTO rentalCard = inquiryUsecase.getRentalCard(new UserInputDTO(userId, ""));

        return ResponseEntity.ok(rentalCard);
    }

    @ApiOperation(value = "대여도서목록 조회", notes = "사용자 정보(id) -> 대여도서목록 조회")
    @GetMapping("/RentalCard/{userId}/rentbook")
    public ResponseEntity<List<RentItemOutputDTO>> getAllRentItem(@PathVariable String userId) {
        List<RentItemOutputDTO> rentItems = inquiryUsecase.getAllRentItem(new UserInputDTO(userId, ""));

        return ResponseEntity.ok(rentItems);
    }

    @ApiOperation(value = "반납도서목록 조회", notes = "사용자 정보(id) -> 반납도서목록 조회")
    @GetMapping("/RentalCard/{userId}/returnbook")
    public ResponseEntity<List<ReturnItemOutputDTO>> getAllReturnItem(@PathVariable String userId) {
        List<ReturnItemOutputDTO> allReturnItem = inquiryUsecase.getAllReturnItem(new UserInputDTO(userId, ""));

        return ResponseEntity.ok(allReturnItem);
    }

    @ApiOperation(value = "대여기능", notes = "사용자정보, 아이템정보1 -> 도서카드 정보 ")
    @PostMapping("/RentalCard/rent")
    public ResponseEntity<RentalCardOutputDTO> rentItem(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO rentalCard = rentItemUsecase.rentItem(userItemInputDTO);

        return ResponseEntity.ok(rentalCard);
    }

    @ApiOperation(value = "반납기능", notes = "사용자정보, 아이템정보1 -> 도서카드 정보 ")
    @PostMapping("/RentalCard/return")
    public ResponseEntity<RentalCardOutputDTO> returnItem(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO rentalCard = returnItemUsecase.returnItem(userItemInputDTO);

        return ResponseEntity.ok(rentalCard);
    }

    @ApiOperation(value = "연체기능", notes = "사용자정보,아이템정보 -> 도서카드 정보")
    @PostMapping("/RentalCard/overdue")
    public ResponseEntity<RentalCardOutputDTO> overdueItem(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO rentalCard = overdueItemUsecase.overDueItem(userItemInputDTO);

        return ResponseEntity.ok(rentalCard);
    }

    @ApiOperation(value = "연체해제기능", notes = "사용자정보,아이템정보 -> 도서카드 정보")
    @PostMapping("/RentalCard/clearoverdue")
    public ResponseEntity<RentalResultOutputDTO> clearOverdueItem(@RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalResultOutputDTO resultDTO = clearOverdueItemUsecase.clearOverdue(clearOverdueInfoDTO);

        return ResponseEntity.ok(resultDTO);
    }


}
