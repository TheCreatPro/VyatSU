import sympy
import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import minimize # для нахождения оптимума


# инициализируем символы x1, x2
x1, x2 = sympy.symbols('x1 x2', real=True)

# Параметры a, b
a, b = 9, 5

# Определяем выражение для f(x1, x2) = (x1 - a)^2 + (x2 - b)^2 + x1*x2
f_expr = (x1 - a) ** 2 + (x2 - b) ** 2 + x1 * x2

# находим градиент (вектор частных производных)
grad_expr = [sympy.diff(f_expr, x1),
             sympy.diff(f_expr, x2)]

# находим гессиан (матрицу вторых производных)
hess_expr = sympy.hessian(f_expr, (x1, x2))
# матрица гессе, состоит из всех вторых частных производных функции
# гессиан показывает, как функция искривляется вокруг точки. он помогает понять, является ли точка мин, макс или седловой


# преобразуем символьные выражения в функции, которые можно вызывать как grad_func(x1_val, x2_val) и hess_func(x1_val, x2_val)
grad_func = sympy.lambdify((x1, x2), grad_expr, 'numpy')
hess_func = sympy.lambdify((x1, x2), hess_expr, 'numpy')


def f(x):
    return (x[0] - a) ** 2 + (x[1] - b) ** 2 + x[0] * x[1]


def method_newton(x0, eps=1e-2, max_iter=100):
    x_k = np.array(x0, dtype=float)
    points = [x_k.copy()]  # для хранения всех итерационных точек

    for i in range(max_iter):
        # Вычисляем градиент и гессиан в текущей точке
        g_val = np.array(grad_func(x_k[0], x_k[1]), dtype=float)
        H_val = np.array(hess_func(x_k[0], x_k[1]), dtype=float)

        # Проверяем норму градиента на критерий останова
        if np.linalg.norm(g_val) < eps:
            break

        # Вычисляем направление Ньютона: p_k = - H^-1 * grad
        p_k = -np.linalg.inv(H_val).dot(g_val)

        # Делаем шаг длины 1: x_{k+1} = x_k + p_k
        x_k = x_k + p_k
        points.append(x_k.copy())

        print(f'Итерация {i + 1}')
        print(f'x = {x_k}')
        print(f'f(x) = {f(x_k)}')
        print(f'df/dx1 = {g_val[0]}; df/dx2 = {g_val[1]}\n')

    return np.array(points)


# запускаем метода Ньютона с автоматическим вычислением градиента/гессиана
x0 = [-10, -10]
points_newton = method_newton(x0, eps=1e-2, max_iter=100)

print('Найденная точка оптимума:', points_newton[-1])
print('Значение функции в точке оптимума:', f(points_newton[-1]))

# Построение графика (контуры функции + траектория итераций)
X, Y = np.meshgrid(np.linspace(-10, 20, 100),
                   np.linspace(-10, 20, 100))
Z = (X - a) ** 2 + (Y - b) ** 2 + X * Y

plt.figure(figsize=(8, 8))
cs = plt.contour(X, Y, Z, levels=50)
plt.clabel(cs, inline=True, fontsize=8)

# Поиск оптимума с помощью метода BFGS (градиентный метод)
res = minimize(f, x0, method='BFGS')
# Добавляем найденную точку на график
plt.plot(res.x[0], res.x[1], 'k*', markersize=12, label='Оптимум (minimize)')

# Траектория метода Ньютона
plt.plot(points_newton[:, 0], points_newton[:, 1], 'ro-', label='Метод Ньютона')

plt.xlabel('x1')
plt.ylabel('x2')
plt.title('Метод Ньютона')  # с автодифференцированием
plt.grid(True)
plt.legend()
plt.show()
