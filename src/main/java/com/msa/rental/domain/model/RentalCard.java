package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentStatus;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.ReturnItem;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentStatus rentStatus;
    private LateFee lateFee;
    private List<RentalItem> rentalItemList = new ArrayList<>();
    private List<ReturnItem> returnItemList = new ArrayList<>();

    public static RentalCard sample() {
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.sample());
        rentalCard.setMember(IDName.sample());
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setLateFee(LateFee.sample());

        return rentalCard;
    }

    private void addRentalItem(RentalItem rentalItem) {
        this.rentalItemList.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        this.rentalItemList.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItemList.add(returnItem);
    }

    private void removeReturnItem(ReturnItem returnItem) {
        this.returnItemList.remove(returnItem);
    }

    public static RentalCard createRentalCard(IDName creator) {
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(creator);
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setLateFee(LateFee.createLateFee());

        return rentalCard;
    }

    // 대여 처리
    public RentalCard rentItem(Item item) {
        checkRentalAvailable();
        addRentalItem(RentalItem.createRentalItem(item));

        return this;
    }

    private void checkRentalAvailable() {
        if(rentStatus == RentStatus.RENT_UNAVAILABLE) {
            throw new IllegalStateException("대여불가 상태입니다.");
        }
        if(this.rentalItemList.size() >= 5) {
            throw new IllegalStateException("이미 5권을 대여했습니다.");
        }
    }

    public RentalCard returnItem(Item item, LocalDate returnDate) {
        RentalItem rentalItem = rentalItemList.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst().orElseThrow(IllegalArgumentException::new);

        calculateLateFee(rentalItem, returnDate);
        addReturnItem(ReturnItem.createReturnItem(rentalItem));
        removeRentalItem(rentalItem);

        return this;
    }

    private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
        LocalDate expireDate = rentalItem.getExpireDate();

        if (returnDate.isAfter(expireDate)) {
            long lateFee = Period.between(expireDate, returnDate).getDays() * 10L;
            setLateFee(this.lateFee.addPoint(lateFee));
        }
    }

    public RentalCard overdueItem(Item item) {
        RentalItem rentalItem = rentalItemList.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        rentalItem.setOverduded(true);
        this.rentStatus = RentStatus.RENT_UNAVAILABLE;

        // 억지로 연체 상태 만들기 - 학습을 위한 임시 코드
        rentalItem.setExpireDate(LocalDate.now().minusDays(1));

        return this;
    }

    public long makeAvailableRental(long point) {
        if(!rentalItemList.isEmpty()) {
            throw new IllegalStateException("모든 도서가 반납되어야 정지를 해제할 수 있습니다.");
        }

        if (this.lateFee.getPoint() != point) {
            throw new IllegalArgumentException("연체 포인트와 동일한 포인트여야 합니다.");
        }

        setLateFee(lateFee.removePoint(point));
        if (lateFee.getPoint() == 0) {
            rentStatus = RentStatus.RENT_AVAILABLE;
        }
        return lateFee.getPoint();
    }
}
