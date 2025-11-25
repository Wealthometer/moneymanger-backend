package in.project.moneymanager.dto;

import java.time.LocalDateTime;

public class CategoryDTO {

    private Long id;
    private String profileId;
    private String name;
    private String icon;
    private String type;// e.g., "income" or "expense"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
