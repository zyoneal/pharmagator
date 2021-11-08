package com.eleks.academy.pharmagator.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String medicineLinkTemplate;

}
