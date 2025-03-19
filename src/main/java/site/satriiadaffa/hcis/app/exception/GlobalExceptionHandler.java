package site.satriiadaffa.hcis.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // ✅ 200 OK (Success) - Tidak perlu ditangani karena ini hanya untuk response sukses

    // ✅ 201 Created - Biasanya dikirim oleh Controller, tidak perlu handler khusus

    // 🔄 3xx Redirection - Tidak umum dalam REST API

    // ❌ 400 Bad Request - JSON salah atau input tidak valid
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonParseError(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "400");
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", "Invalid JSON format. Please check your request body.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

//    // ❌ 401 Unauthorized - Token login salah atau tidak ada
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<Map<String, String>> handleUnauthorizedError(AccessDeniedException ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("status", "401");
//        errorResponse.put("error", "Unauthorized");
//        errorResponse.put("message", "You are not authorized to access this resource.");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//    }

    // ❌ 403 Forbidden - Token benar tapi tidak punya izin akses
    // ❌ 404 Not Found - Data tidak ditemukan
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        HttpStatus status = (HttpStatus) ex.getStatusCode(); // Ambil status HTTP dari exception

        errorResponse.put("status", String.valueOf(status.value()));
        errorResponse.put("error", status.getReasonPhrase());

        // Tentukan pesan error berdasarkan status kode
        switch (status) {
            case FORBIDDEN:
                errorResponse.put("message", "Access is forbidden for this resource.");
                break;
            case NOT_FOUND:
                errorResponse.put("message", "The requested resource was not found.");
                break;
            default:
                errorResponse.put("message", "An unexpected error occurred.");
        }

        return ResponseEntity.status(status).body(errorResponse);
    }

    // ❌ 405 Method Not Allowed - Misalnya pakai GET ke endpoint yang butuh POST
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotAllowedError(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "405");
        errorResponse.put("error", "Method Not Allowed");
        errorResponse.put("message", "This HTTP method is not allowed for this endpoint.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    // 🚨 500 Internal Server Error - Jika terjadi kesalahan yang tidak terduga
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "500");
        errorResponse.put("error", "Internal Server Error");
        errorResponse.put("message", "Something went wrong. Please contact support.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
