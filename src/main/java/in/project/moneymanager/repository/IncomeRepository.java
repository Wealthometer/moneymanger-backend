package in.project.moneymanager.repository;

import in.project.moneymanager.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository <IncomeEntity, Long> {
}
