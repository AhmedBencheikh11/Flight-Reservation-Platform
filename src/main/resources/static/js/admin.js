document.addEventListener('DOMContentLoaded', function () {
    // Get references to the necessary elements
    const reservationsSection = document.querySelector('.reservation-list');
    const reservationsButton = document.querySelector('.action-btn');

    // Check if the reservation section exists
    if (reservationsSection) {
        // Initially hide the reservations section if there are no reservations
        reservationsSection.style.display = 'none';

        // Add event listener for the "Mes réservations" button
        reservationsButton.addEventListener('click', function (event) {
            event.preventDefault();

            // Toggle visibility of the reservations section
            if (reservationsSection.style.display === 'none' || reservationsSection.style.display === '') {
                reservationsSection.style.display = 'block';
            } else {
                reservationsSection.style.display = 'none';
            }
        });
    }
});
