package com.project.kkiaprj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QryResult {
    int count;  // 결괏값 (정수)
    String status;  // 결과 메세지
}
