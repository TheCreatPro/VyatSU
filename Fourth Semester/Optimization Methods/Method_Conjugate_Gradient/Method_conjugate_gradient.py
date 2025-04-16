import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import minimize_scalar
from scipy.optimize import minimize # для нахождения оптимума

a, b, c = 9, 5, 1  # параметры функции


def f(x):  # функция
    return (x[0] - a) ** 2 + (x[1] - b) ** 2 + c * x[0] * x[1]


def grad_f(x):  # градиент функции
    return np.array([2 * (x[0] - a) + c * x[1], 2 * (x[1] - b) + c * x[0]])


def method_gradient(x0, epsilon=1e-2, max_iter=100):  # метод сопряжённых градиентов
    x_k = np.array(x0, dtype=float)
    g_k = -grad_f(x_k)
    s_k = g_k.copy()
    points = [x_k.copy()]
    count = 1

    for _ in range(max_iter):
        # одномерный поиск альфа в направлении s_k
        def phi(alpha):
            return f(x_k + alpha * s_k)

        res = minimize_scalar(phi) # ищет минимум для одномерной функции
        # используется, чтобы найти оптимальный размер шага (α) при движении к минимуму функции
        alpha_opt = res.x

        # обновление точки
        x_k = x_k + alpha_opt * s_k
        g_k_new = -grad_f(x_k)
        beta_k = np.dot(g_k_new, g_k_new) / np.dot(g_k, g_k)
        s_k = g_k_new + beta_k * s_k
        g_k = g_k_new
        points.append(x_k.copy())
        print('Итерация', count, points[-1], '\nf(x1, x2) =', f(points[-1]), '\ndf/dx1 = ', grad_f(x_k)[0], '\ndf/dx2 = ', grad_f(x_k)[1], '\n')
        count += 1

        if np.linalg.norm(g_k) < epsilon:
            break

    return np.array(points)


# начальная точка
x0 = [-10, -10]
points = method_gradient(x0)
print('Найденная точка оптимума методом сопряжённых градиентов:', points[-1])
print('Значение функции в точке оптимума:', f(points[-1]))

# строим график
X, Y = np.meshgrid(np.linspace(-10, 20),
                   np.linspace(-10, 20))
Z = f([X, Y])
plt.figure(figsize=(8, 8)) # это чтобы был виден прямой угол
# между первым и вторым направлением будет прямой угол, поэтому эти направления сопряжённые
plt.contour(X, Y, Z, levels=50)

# Поиск оптимума с помощью метода BFGS (градиентный метод)
res = minimize(f, x0, method='BFGS')
# Добавляем найденную точку на график
plt.plot(res.x[0], res.x[1], 'k*', markersize=12, label='Оптимум (minimize)')


plt.plot(points[:, 0], points[:, 1], 'ro-', label='Метод сопряжённых градиентов')
plt.xlabel('x1')
plt.ylabel('x2')
plt.title('Метод сопряжённых градиентов')
plt.legend()
plt.grid()
plt.show()
