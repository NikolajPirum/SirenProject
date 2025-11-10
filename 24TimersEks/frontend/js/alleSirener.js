document.addEventListener("DOMContentLoaded", () => {
    fetchSirens();
});

// Hent og vis alle sirener
function fetchSirens() {
    fetch("http://localhost:8080/sirens")
        .then(res => res.json())
        .then(sirens => {
            const tbody = document.getElementById("sirens-tbody");
            tbody.innerHTML = "";

            sirens.forEach(siren => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${siren.sirenId}</td>
                    <td>${siren.latitude}</td>
                    <td>${siren.longitude}</td>
                    <td>${siren.status}</td>
                    <td>${siren.status === "DISABLED" ? "Ja" : "Nej"}</td>
                    <td>
                        <button onclick="editSiren(${siren.sirenId})">Rediger</button>
                        <button onclick="deleteSiren(${siren.sirenId})">Slet</button>
                    </td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(err => console.error("Fejl ved hentning af sirener:", err));
}

// Åbn redigeringsmodal med data
function editSiren(id) {
    fetch("http://localhost:8080/sirens")
        .then(res => res.json())
        .then(sirens => {
            const siren = sirens.find(s => s.sirenId === id);
            if (!siren) return alert("Sirene ikke fundet.");

            document.getElementById("edit-id").value = siren.sirenId;
            document.getElementById("edit-lat").value = siren.latitude;
            document.getElementById("edit-lon").value = siren.longitude;
            document.getElementById("edit-status").value = siren.status;

            document.getElementById("edit-modal").classList.remove("hidden");
        });
}

// Slet en sirene
function deleteSiren(id) {
    if (!confirm("Er du sikker på, at du vil slette sirenen?")) return;

    fetch(`http://localhost:8080/sirens/${id}`, {
        method: "DELETE"
    })
        .then(res => {
            if (res.ok) {
                fetchSirens();
            } else {
                res.text()
                alert("Det lykkedes ikke at slette.");


            }
        })
        .catch(err => console.error("Fejl ved sletning:", err));
}

// Luk redigeringsmodal
function closeModal() {
    document.getElementById("edit-modal").classList.add("hidden");
}

// Gem ændringer fra redigeringsformular
document.getElementById("edit-form").addEventListener("submit", function (e) {
    e.preventDefault();

    const id = document.getElementById("edit-id").value;
    const lat = parseFloat(document.getElementById("edit-lat").value);
    const lon = parseFloat(document.getElementById("edit-lon").value);
    const status = document.getElementById("edit-status").value;

    fetch(`http://localhost:8080/sirens/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ latitude: lat, longitude: lon, status })
    })
        .then(res => {
            if (res.ok) {
                closeModal();
                fetchSirens();
            } else {
                alert("Opdatering mislykkedes.");
            }
        })
        .catch(err => console.error("Fejl ved opdatering:", err));
});

// Åbn/luk oprettelsesmodal
function openCreateModal() {
    document.getElementById("create-siren-modal").classList.remove("hidden");
}

function closeCreateModal() {
    document.getElementById("create-siren-modal").classList.add("hidden");
}

// Opret ny sirene
document.getElementById("create-siren-form").addEventListener("submit", function (e) {
    e.preventDefault();

    const lat = parseFloat(document.getElementById("create-lat").value);
    const lon = parseFloat(document.getElementById("create-lon").value);
    const status = document.getElementById("create-status").value;

    fetch("http://localhost:8080/sirens/newSiren", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ latitude: lat, longitude: lon, status })
    })
        .then(res => {
            if (res.ok) {
                closeCreateModal();
                fetchSirens();
            } else {
                alert("Kunne ikke oprette sirene.");
            }
        })
        .catch(err => console.error("Fejl ved oprettelse:", err));
});
