package com.checkinn.guest.repository;

import com.checkinn.guest.model.GuestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestLogRepository extends JpaRepository<GuestLog, Long> {
}
