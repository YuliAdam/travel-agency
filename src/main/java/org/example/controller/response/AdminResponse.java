package org.example.controller.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Admin;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private Long id;
    private String adminName;
    public AdminResponse(Admin admin){
        this.id=admin.getId();
        this.adminName=admin.getAdminName();
    }
}
