

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        const taskName = document.querySelector("input[name='name']").value;
        if (!taskName.trim()) {
            alert("Task name is required!");
            event.preventDefault();
        }
    });

    const deleteButtons = document.querySelectorAll(".btn-delete");
    deleteButtons.forEach(button => {
        button.addEventListener("click", function (event) {
            const confirmDelete = confirm("Are you sure you want to delete this task?");
            if (!confirmDelete) {
                event.preventDefault();
            }
        });
    });
});