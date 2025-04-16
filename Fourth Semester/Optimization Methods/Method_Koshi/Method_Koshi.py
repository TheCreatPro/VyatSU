import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import minimize_scalar
from scipy.optimize import minimize # для нахождения оптимума

a, b, c = 9, 5, 1  # параметры функции


def f(x):  # функция
    return (x[0] - a) ** 2 + (x[1] - b) ** 2 + c * x[0] * x[1]


def grad_f(x):  # градиент функции
    return np.array([2 * (x[0] - a) + c * x[1], 2 * (x[1] - b) + c * x[0]])


def method_Koshi(x0, epsilon=1e-1):  # метод Коши
    x_k = np.array(x0, dtype=float)
    points = [x_k.copy()]
    count = 1;
    while True:
        grad = grad_f(x_k)
        norm_grad = np.linalg.norm(grad)

        if norm_grad < epsilon:
            break

        # одномерный поиск альфа в направлении антиградиента
        def phi(alpha):
            return f(x_k - alpha * grad)

        res = minimize_scalar(phi)
        alpha_opt = res.x

        # обновление точки
        x_k = x_k - alpha_opt * grad
        points.append(x_k.copy())
        print('Итерация', count, points[-1])
        count += 1
    return np.array(points)


# начальная точка
x0 = [-10, -10]
points = method_Koshi(x0)
print('Найденная точка оптимума методом Коши:', points[-1])
print('Значение функции в точке оптимума:', f(points[-1]))

# строим график
X, Y = np.meshgrid(np.linspace(-10, 20),
                   np.linspace(-10, 20))
Z = f([X, Y])
plt.contour(X, Y, Z, levels=50)

# Поиск оптимума с помощью метода BFGS (градиентный метод)
res = minimize(f, x0, method='BFGS')
# Добавляем найденную точку на график
plt.plot(res.x[0], res.x[1], 'k*', markersize=12, label='Оптимум (minimize)')

plt.plot(points[:, 0], points[:, 1], 'ro-', label='Метод Коши')
plt.xlabel('x1')
plt.ylabel('x2')
plt.title('Метода Коши')
plt.legend()
plt.grid()
plt.show()
