package com.msa.rental.domain.model.vo;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {
    private Integer no;
    private String title;

    public static Item sample() {
        return new Item(10, "MSA 사실과 오해");
    }
}
