package in.project.moneymanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class IncomeDTO {

    private Long id;
    private String name;
    private String icon;
    private String categoryName;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDate date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
