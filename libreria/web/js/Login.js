function login() {
    let user = document.getElementById("txtCorreo").value;
    let contrasenia = document.getElementById("txtPassword").value;


    //encriptar(contrasenia).then((textoEncriptado) => {
    //alert(textoEncriptado.toString());
    let usuario = {datosUsuario: JSON.stringify({usuario: user, contrasenia: contrasenia})};

    const url = new URLSearchParams(usuario);
    fetch('http://localhost:8081/libreria/api/restlibreria/login',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else if (data.idEmpleado) {
                    //localStorage.setItem('currentUser', JSON.stringify(data));
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Acceso Concedido' + data.persona.nombre,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    setTimeout(() => {
                        console.log("2 segundos esperado");
                    }, 2000);

                    //window.location.href = "http://localhost:8081/libreria/principal.html";

                } else {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'uyyyy...',
                        text: 'Ha ocurrido un error'
                    });
                }
                JSON.stringify(data);
                //limpiarForm();
            });
    //});

}