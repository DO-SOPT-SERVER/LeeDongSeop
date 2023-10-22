package com.example.secondSeminar.dto.request;

import com.example.secondSeminar.enums.Part;
import lombok.Data;

@Data
public class MemberProfileUpdateRequest {
    private int generation;
    private Part part;
}
