package com.codewithbrice.librarymanagementsystem.service.Impl;

import com.codewithbrice.librarymanagementsystem.mapper.SubscriptionPlanMapper;
import com.codewithbrice.librarymanagementsystem.modal.SubscriptionPlan;
import com.codewithbrice.librarymanagementsystem.modal.User;
import com.codewithbrice.librarymanagementsystem.payload.dto.SubscriptionPlanDTO;
import com.codewithbrice.librarymanagementsystem.repository.SubscriptionPlanRepository;
import com.codewithbrice.librarymanagementsystem.service.SubscriptionPlanService;
import com.codewithbrice.librarymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository planRepository;
    private final SubscriptionPlanMapper planMapper;
    private final UserService user;
    private final UserService userService;

    @Override
    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) throws Exception {

        if(planRepository.existsByPlanCode(planDTO.getPlanCode())) {
            throw new Exception("plan code is already exist");
        }
        SubscriptionPlan plan = planMapper.toEntity(planDTO);

        User currentUser = userService.getCurrentUser();
        plan.setCreatedBy(currentUser.getFullName());
        plan.setUpdateBy(currentUser.getFullName());

        SubscriptionPlan savedPlan = planRepository.save(plan);

        return planMapper.toDTO(savedPlan);
    }

    @Override
    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) throws Exception {

        SubscriptionPlan existingPlan = planRepository.findById(planId).orElseThrow(
                ()->new Exception("Plan not found!")
        );

        planMapper.updateEntity(existingPlan, planDTO);

        User currentUser=userService.getCurrentUser();
        existingPlan.setUpdateBy(currentUser.getFullName());

        SubscriptionPlan updatedPlan = planRepository.save(existingPlan);
        return planMapper.toDTO(updatedPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long planId) throws Exception {

        SubscriptionPlan existingPlan = planRepository.findById(planId).orElseThrow(
                ()->new Exception("Plan not found!")
        );

        planRepository.delete(existingPlan);
    }

    @Override
    public List<SubscriptionPlanDTO> getAllSubscriptionPlan() {
        List<SubscriptionPlan> planList=planRepository.findAll();
        return planList.stream().map(
                planMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public SubscriptionPlanDTO getSubscriptionPlan(Long planId) {
        return null;
    }
}
