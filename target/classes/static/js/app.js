const API_BASE = '/api';

function getCurrentUser() {
    const userStr = localStorage.getItem('currentUser');
    return userStr ? JSON.parse(userStr) : null;
}

function setCurrentUser(user) {
    if (user) {
        localStorage.setItem('currentUser', JSON.stringify(user));
        localStorage.setItem('customerId', user.id);
    } else {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('customerId');
    }
    renderAuthNav();
}

function getCustomerId() {
    const user = getCurrentUser();
    return user ? user.id : '1';
}

function logoutUser() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('customerId');
    showAlert('Successfully logged out', 'success');
    setTimeout(() => {
        window.location.href = '/login.html';
    }, 1000);
}

function renderAuthNav() {
    const navContainer = document.getElementById('auth-nav-items');
    if (!navContainer) return;

    const user = getCurrentUser();
    if (user) {
        navContainer.innerHTML = `
            <span class="navbar-text me-3 text-white fw-bold"><i class="fas fa-user-circle me-1"></i> ${user.name}</span>
            <button class="btn btn-outline-light btn-sm" onclick="logoutUser()"><i class="fas fa-sign-out-alt me-1"></i> Logout</button>
        `;
    } else {
        navContainer.innerHTML = `
            <a href="/login.html" class="btn btn-outline-light btn-sm me-2"><i class="fas fa-sign-in-alt me-1"></i> Login</a>
            <a href="/register.html" class="btn btn-warning btn-sm text-dark fw-bold"><i class="fas fa-user-plus me-1"></i> Register</a>
        `;
    }
}

async function updateCartCount() {
    const customerId = getCustomerId();
    try {
        const res = await fetch(`${API_BASE}/cart/${customerId}`);
        if (res.ok) {
            const items = await res.json();
            const count = items.reduce((sum, item) => sum + item.quantity, 0);
            const badge = document.getElementById('cart-count');
            if (badge) {
                badge.innerText = count;
            }
        }
    } catch (err) {
        console.error('Failed to load cart count', err);
    }
}

function showAlert(message, type = 'success') {
    const alertContainer = document.getElementById('alert-container');
    if (!alertContainer) return;
    alertContainer.innerHTML = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    setTimeout(() => {
        alertContainer.innerHTML = '';
    }, 4000);
}

document.addEventListener('DOMContentLoaded', () => {
    renderAuthNav();
    updateCartCount();
});
