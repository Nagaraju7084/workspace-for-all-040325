package com.naipunya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naipunya.entity.AuditLog;

public interface AuditLogRepository  extends JpaRepository<AuditLog,Long> {
}
