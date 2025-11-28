let currentReportData = null; // Para exportar JSON
let currentReportType = ''; //Para exportar PDF

// --- LÓGICA DE SELECCIÓN ---
// Hacemos la función ASYNC para poder usar 'await' en las peticiones al servidor
async function selectReport(type, cardElement) {
    // 1. Guardamos el tipo de reporte actual para usarlo luego en el PDF
    currentReportType = type;
    // 1. UI Selection
    document.querySelectorAll('.report-card').forEach(c => c.classList.remove('active'));
    cardElement.classList.add('active');

    // 2. Show Container & Loading State
    document.getElementById('emptyState').style.display = 'none';
    const container = document.getElementById('reportContainer');
    const contentEl = document.getElementById('reportContent');
    
    container.style.display = 'block';
    // Mostramos un mensaje de carga mientras el servidor responde
    contentEl.innerHTML = '<div style="padding:20px; text-align:center; color:#666;">Cargando datos del servidor...</div>';

    // 3. Setup Header Elements
    const titleEl = document.getElementById('reportTitle');
    const dateEl = document.getElementById('reportDate');
    const filtersEl = document.getElementById('dynamicFilters');
    
    dateEl.textContent = `Generado: ${new Date().toLocaleDateString()}`;

    // --- LÓGICA POR TIPO DE REPORTE ---

    if (type === 'mascota') {
        titleEl.textContent = "Reporte General de Mascotas";
        // Filtros visuales (Funcionalidad de filtrado pendiente para futura fase)
        filtersEl.innerHTML = `
            <label style="font-weight:600; margin-right:8px;">Filtrar:</label>
            <input type="text" class="filter-input" placeholder="Buscar por nombre..." onkeyup="filtrarTablaLocal(this.value)">
        `;
        
        try {
            // CONEXIÓN REAL: Llamamos a tu Servlet Java
            // La ruta es relativa: localhost:8080/polopets/api/mascotas
            const response = await fetch('api/mascotas');
            
            if (!response.ok) throw new Error('Error en la red');
            
            const data = await response.json(); // Convertimos el JSON a Objetos JS
            renderPetReport(contentEl, data);   // Pasamos los datos reales a la vista
            
        } catch (error) {
            console.error('Error:', error);
            contentEl.innerHTML = `<p style="color:red; padding:20px;">Error al cargar datos: ${error.message}</p>`;
        }
    } 
    
    else if (type === 'cliente') {
        titleEl.textContent = "Directorio de Clientes";
        // Filtro simple para buscar clientes
        filtersEl.innerHTML = `
            <label style="font-weight:600; margin-right:8px;">Buscar:</label>
            <input type="text" class="filter-input" placeholder="Nombre o ID..." onkeyup="filtrarTablaClientes(this.value)">
        `;
        
        try {
            // ASUMIMOS que tu Servlet de clientes responde en "/api/clientes"
            const response = await fetch('api/cliente');
            
            if (!response.ok) throw new Error('Error al obtener clientes');
            
            const data = await response.json();
            renderClientReport(contentEl, data);
            
        } catch (error) {
            console.error('Error:', error);
            contentEl.innerHTML = `<p style="color:red; padding:20px;">Error: ${error.message}</p>`;
        }
    }
    else if (type === 'ventas') {
        titleEl.textContent = "Reporte General de Ventas";
        // Mantenemos el filtro visual de fechas
        filtersEl.innerHTML = `
            <label style="font-weight:600; margin-right:8px;">Rango:</label>
            <input type="date" class="filter-input" id="fechaInicio">
            <span style="margin:0 8px">a</span>
            <input type="date" class="filter-input" id="fechaFin">
            <button class="btn btn-secondary" onclick="filtrarVentasPorFecha()" style="padding:6px 12px; font-size:0.8rem; margin-left:8px;">Filtrar</button>
        `;
        
        try {
            // Llamamos a tu Servlet
            const response = await fetch('api/ventas');
            
            if (!response.ok) throw new Error('Error al obtener ventas');
            
            const data = await response.json();
            renderSalesReport(contentEl, data); // Renderizamos con datos reales
            
        } catch (error) {
            console.error('Error:', error);
            contentEl.innerHTML = `<p style="color:red; padding:20px;">Error: ${error.message}</p>`;
        }
    }
    else if (type === 'consultas') {
        titleEl.textContent = "Historial Médico de Consultas";
        
        // Cambiamos el filtro de estado por un buscador de síntomas/mascota
        filtersEl.innerHTML = `
            <label style="font-weight:600; margin-right:8px;">Buscar:</label>
            <input type="text" class="filter-input" placeholder="Síntoma o ID Mascota..." onkeyup="filtrarConsultas(this.value)">
        `;

        try {
            // Petición al API
            const response = await fetch('api/consultas');
            
            if (!response.ok) throw new Error('Error al obtener consultas');
            
            const data = await response.json();
            renderConsultasReport(contentEl, data);
            
        } catch (error) {
            console.error('Error:', error);
            contentEl.innerHTML = `<p style="color:red; padding:20px;">Error: ${error.message}</p>`;
        }
    }
}

// --- RENDERIZADORES ---

// Modificado para recibir datos reales (lista de mascotas)
function renderPetReport(container, data) {
    currentReportData = data; // Guardamos para el botón de exportar JSON

    if (data.length === 0) {
        container.innerHTML = '<p style="padding:20px;">No hay mascotas registradas.</p>';
        return;
    }

    // Mapeamos los campos EXACTOS de tu MascotaDTO
    // nota: en JSON los booleanos true/false se ven directos
    let rows = data.map(m => `
        <tr>
            <td style="font-weight:bold;">${m.nombre}</td>
            <td>${m.tipo} - ${m.raza}</td>
            <td>${m.edad} años</td>
            <td>${m.peso}</td>
            <td>
                <span style="
                    padding:4px 8px; 
                    border-radius:12px; 
                    font-size:0.85rem; 
                    background-color: ${m.estaAdoptado ? '#dcfce7' : '#fee2e2'};
                    color: ${m.estaAdoptado ? '#166534' : '#991b1b'};
                ">
                    ${m.estaAdoptado ? 'Adoptado' : 'Disponible'}
                </span>
            </td>
            <td>${m.fechaIngreso || '--'}</td>
        </tr>
    `).join('');

    container.innerHTML = `
        <div class="report-summary">
            <div class="summary-item">
                <span class="summary-label">Total Registrados</span>
                <span class="summary-value">${data.length}</span>
            </div>
            <div class="summary-item">
                <span class="summary-label">Disponibles</span>
                <span class="summary-value">${data.filter(m => !m.estaAdoptado).length}</span>
            </div>
        </div>

        <div style="overflow-x:auto;">
            <table class="report-table">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Detalles</th>
                        <th>Edad</th>
                        <th>Peso</th>
                        <th>Estado</th>
                        <th>Ingreso</th>
                    </tr>
                </thead>
                <tbody id="tablaMascotasBody">${rows}</tbody>
            </table>
        </div>
    `;
}

function renderClientReport(container, data) {
    currentReportData = data; // Para exportar a JSON

    if (!data || data.length === 0) {
        container.innerHTML = '<p style="padding:20px;">No hay clientes registrados.</p>';
        return;
    }

    // Mapeo de datos: OJO, los nombres de propiedades (.nombre, .numeroContacto)
    // deben ser IDÉNTICOS a como se llaman en tu ClienteDTO de Java.
    let rows = data.map(c => `
        <tr>
            <td style="font-weight:bold;">${c.nombre}</td>
            <td>${c.id}</td>
            <td>${c.numeroContacto}</td>
            <td>${c.direccionContacto}</td>
            <td>
                <span style="background:#f3f4f6; padding:2px 6px; border-radius:4px; font-size:0.85rem;">
                    ${c.idMascotasCargo || 'Ninguna'}
                </span>
            </td>
        </tr>
    `).join('');

    container.innerHTML = `
        <div class="report-summary">
            <div class="summary-item">
                <span class="summary-label">Total Clientes</span>
                <span class="summary-value">${data.length}</span>
            </div>
        </div>

        <div style="overflow-x:auto;">
            <table class="report-table">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>ID Cliente</th>
                        <th>Teléfono</th>
                        <th>Dirección</th>
                        <th>Mascotas (IDs)</th>
                    </tr>
                </thead>
                <tbody id="tablaClientesBody">${rows}</tbody>
            </table>
        </div>
    `;
}

function renderSalesReport(container, data) {
    currentReportData = data;

    if (!data || data.length === 0) {
        container.innerHTML = '<p style="padding:20px;">No hay ventas registradas.</p>';
        return;
    }

    // Calcular el total vendido sumando la propiedad valorVenta
    const totalVentas = data.reduce((suma, venta) => suma + venta.valorVenta, 0);

    // Formateador de moneda (para que salga $150,000.00)
    const formatoMoneda = new Intl.NumberFormat('es-CO', { style: 'currency', currency: 'COP' });

    let rows = data.map(v => `
        <tr>
            <td>${v.fecha}</td>
            <td style="font-weight:bold;">${v.idVenta}</td>
            <td>${v.idCliente}</td>
            <td>${v.idMascota}</td>
            <td style="font-weight:bold; color:var(--c-verde); text-align:right;">
                ${formatoMoneda.format(v.valorVenta)}
            </td>
        </tr>
    `).join('');

    container.innerHTML = `
        <div class="report-summary">
            <div class="summary-item">
                <span class="summary-label">Transacciones</span>
                <span class="summary-value">${data.length}</span>
            </div>
            <div class="summary-item">
                <span class="summary-label">Total Recaudado</span>
                <span class="summary-value" style="color:var(--c-verde);">
                    ${formatoMoneda.format(totalVentas)}
                </span>
            </div>
        </div>

        <div style="overflow-x:auto;">
            <table class="report-table">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>ID Venta</th>
                        <th>ID Cliente</th>
                        <th>ID Mascota</th>
                        <th style="text-align:right;">Valor</th>
                    </tr>
                </thead>
                <tbody id="tablaVentasBody">${rows}</tbody>
            </table>
        </div>
    `;
}

function renderConsultasReport(container, data) {
    currentReportData = data;

    if (!data || data.length === 0) {
        container.innerHTML = '<p style="padding:20px;">No hay consultas registradas.</p>';
        return;
    }

    let rows = data.map(c => {
        // Lógica visual: Si tiene tratamiento, es "verde", si no, quizás requiere atención
        const tieneTratamiento = c.tratamiento && c.tratamiento.length > 0;
        
        return `
        <tr>
            <td style="white-space:nowrap;">${c.fecha}</td>
            <td style="font-weight:bold;">${c.idMascota}</td>
            <td>${c.sintomas}</td>
            <td>
                ${tieneTratamiento 
                    ? c.tratamiento 
                    : '<span style="color:red; font-style:italic;">Pendiente de asignar</span>'}
            </td>
            <td>
                <span style="font-size:0.8rem; font-weight:bold; color:${tieneTratamiento ? 'var(--c-verde)' : 'orange'}">
                    ${c.idConsulta}
                </span>
            </td>
        </tr>
        `;
    }).join('');

    container.innerHTML = `
        <div class="report-summary">
            <div class="summary-item">
                <span class="summary-label">Consultas Totales</span>
                <span class="summary-value">${data.length}</span>
            </div>
        </div>

        <div style="overflow-x:auto;">
            <table class="report-table">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>ID Mascota</th>
                        <th style="width:30%">Síntomas</th>
                        <th style="width:30%">Tratamiento</th>
                        <th>Ref.</th>
                    </tr>
                </thead>
                <tbody id="tablaConsultasBody">${rows}</tbody>
            </table>
        </div>
    `;
}

// --- UTILIDADES ---

// Función extra para filtrar la tabla sin ir al backend
function filtrarTablaLocal(texto) {
    const value = texto.toLowerCase();
    const filas = document.querySelectorAll("#tablaMascotasBody tr");
    filas.forEach(fila => {
        const textoFila = fila.innerText.toLowerCase();
        fila.style.display = textoFila.includes(value) ? "" : "none";
    });
}

function filtrarTablaClientes(texto) {
    const value = texto.toLowerCase();
    const filas = document.querySelectorAll("#tablaClientesBody tr");
    filas.forEach(fila => {
        const textoFila = fila.innerText.toLowerCase();
        fila.style.display = textoFila.includes(value) ? "" : "none";
    });
}

function filtrarVentasPorFecha() {
    const inicio = document.getElementById('fechaInicio').value;
    const fin = document.getElementById('fechaFin').value;
    
    if (!currentReportData) return;

    // Filtramos el array original guardado en currentReportData
    const ventasFiltradas = currentReportData.filter(v => {
        if (!inicio && !fin) return true;
        const fechaVenta = new Date(v.fecha);
        const fechaInicio = inicio ? new Date(inicio) : new Date('1900-01-01');
        const fechaFin = fin ? new Date(fin) : new Date('2100-01-01');
        
        return fechaVenta >= fechaInicio && fechaVenta <= fechaFin;
    });

    // Re-renderizamos solo la tabla con los datos filtrados
    const container = document.getElementById('reportContent');
    renderSalesReport(container, ventasFiltradas);
    showToast("Filtro de fecha aplicado");
}

function filtrarConsultas(texto) {
    const value = texto.toLowerCase();
    const filas = document.querySelectorAll("#tablaConsultasBody tr");
    filas.forEach(fila => {
        const textoFila = fila.innerText.toLowerCase();
        // Muestra la fila si encuentra coincidencia en síntomas o ID de mascota
        fila.style.display = textoFila.includes(value) ? "" : "none";
    });
}

function resetReport() {
    document.querySelectorAll('.report-card').forEach(c => c.classList.remove('active'));
    document.getElementById('reportContainer').style.display = 'none';
    document.getElementById('emptyState').style.display = 'flex';
}

function exportJSON() {
    if (!currentReportData) return;
    const dataStr = JSON.stringify(currentReportData, null, 2);
    const blob = new Blob([dataStr], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = "reporte_mr_polo.json";
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    showToast("JSON generado correctamente.");
}

function downloadPDF() {
    // Validación: Si no ha seleccionado nada, no hacemos nada
    if (!currentReportType) {
        showToast("Por favor selecciona un reporte primero.");
        return;
    }

    showToast("Generando PDF...");

    // Construimos la URL enviando el parámetro "tipo" al Servlet
    // Ejemplo: reportes/pdf?tipo=mascota
    const url = `reportes/pdf?tipo=${currentReportType}`;

    // Abrimos en nueva pestaña para iniciar la descarga
    window.open(url, '_blank');
}

function showToast(msg) {
    const toast = document.getElementById('toast');
    if(toast) {
        document.getElementById('toastMessage').textContent = msg;
        toast.classList.add('show');
        setTimeout(() => toast.classList.remove('show'), 3000);
    }
}