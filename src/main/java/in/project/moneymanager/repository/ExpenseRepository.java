package in.project.moneymanager.repository;

import in.project.moneymanager.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository <ExpenseEntity, Long> {

}
