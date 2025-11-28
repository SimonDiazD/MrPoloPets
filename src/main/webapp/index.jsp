<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes - Mr. Polo Pets</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- CSS del proyecto -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar">
        <div class="nav-brand">
            <img src="https://cdn-icons-png.flaticon.com/512/1076/1076928.png" alt="Logo Mr. Polo Pets">
            <span>Mr. Polo Pets</span>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index.jsp" class="nav-item active">Reportes</a></li>
        </ul>
    </nav>

    <main class="main-container">
        
        <header class="page-header">
            <div class="page-title">
                <h1>Reportes</h1>
                <p>Genera y exporta estadísticas detalladas del negocio.</p>
            </div>
        </header>

        <!-- Selector de Reportes -->
        <section class="reports-grid">
            <div class="report-card" onclick="selectReport('cliente', this)">
                <div class="report-icon">👤</div>
                <span class="report-name">Reporte por Cliente</span>
            </div>
            <div class="report-card" onclick="selectReport('mascota', this)">
                <div class="report-icon">🐾</div>
                <span class="report-name">Reporte por Mascota</span>
            </div>
            <div class="report-card" onclick="selectReport('consultas', this)">
                <div class="report-icon">🩺</div>
                <span class="report-name">Reporte de Consultas</span>
            </div>
            <div class="report-card" onclick="selectReport('ventas', this)">
                <div class="report-icon">💰</div>
                <span class="report-name">Reporte de Ventas</span>
            </div>
        </section>

        <!-- Panel de Vista Previa -->
        <section class="preview-section">

            <!-- Estado Vacío Inicial -->
            <div id="emptyState" class="empty-state">
                <div class="empty-icon">📊</div>
                <h3 style="margin-bottom: 8px; color: var(--c-cafe-oscuro);">Genera un reporte para comenzar</h3>
                <p style="color: var(--c-gris-calido); margin-bottom: 24px;">Selecciona una opción arriba para visualizar los datos.</p>
            </div>

            <!-- Contenedor del Reporte -->
            <div id="reportContainer" style="display: none; width: 100%;">
                
                <div class="preview-header">
                    <div class="preview-title">
                        <h2 id="reportTitle">Título del Reporte</h2>
                        <span class="preview-meta" id="reportDate">Generado: --/--/----</span>
                    </div>
                    <button class="btn btn-outline" onclick="resetReport()">Limpiar</button>
                </div>

                <!-- Filtros dinámicos -->
                <div id="dynamicFilters" class="dynamic-filters"></div>

                <div id="reportContent" class="report-content"></div>

                <div class="preview-actions">
                    <button class="btn btn-secondary" onclick="exportJSON()">
                        Exportar JSON
                    </button>
                    <button class="btn btn-primary" onclick="downloadPDF()">
                        Descargar PDF
                    </button>
                </div>
            </div>

        </section>

    </main>

    <!-- Toast -->
    <div class="toast" id="toast">
        <svg width="24" height="24" fill="#4ade80" viewBox="0 0 24 24"><path d="M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z"/></svg>
        <span id="toastMessage">Acción completada.</span>
    </div>
    
    <!-- JS del proyecto -->
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>

