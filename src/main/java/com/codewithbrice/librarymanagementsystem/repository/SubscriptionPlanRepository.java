package com.codewithbrice.librarymanagementsystem.repository;

import com.codewithbrice.librarymanagementsystem.modal.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    Boolean existsByPlanCode(String planCode);
}
