package com.project.kkiaprj.listener;

import java.time.LocalDateTime;

public interface Auditable {

    LocalDateTime getCreatedDate();

    void setCreatedDate(LocalDateTime createdDate);
}
