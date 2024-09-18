package ci.atos.apireservationservicedomicile.enumerations;

public enum BookingStatus {
    PENDING,     // Réservation créée mais non encore traitée , en attente
    CONFIRMED,   // Réservation confirmée
    IN_PROGRESS, // Réservation en cours de traitement
    COMPLETED,   // Réservation terminée
    CANCELED     // Réservation annulée
}
