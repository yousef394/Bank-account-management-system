package com.bank_account_management_system.model;

import java.util.List;

public interface Auditable {
    List<String> getAuditLog();
}
