package com.bank_account_management_system.model;

import java.io.IOException;
import java.util.List;

public interface Auditable {
    List<String> getAuditLog() throws IOException;
}
