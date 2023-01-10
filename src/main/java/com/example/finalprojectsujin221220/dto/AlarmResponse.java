package com.example.finalprojectsujin221220.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlarmResponse {
    private List<AlarmListResponse> content;

}
