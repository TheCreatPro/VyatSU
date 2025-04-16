import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import minimize # для нахождения оптимума

a, b = 9, 5
def f(x1, x2, a=9, b=5):
    return (x1 - a) ** 2 + (x2 - b) ** 2 + x1 * x2


def minimum_search(func, x1, x2, direction, eps=1e-6):
    # одномерный поиск минимума методом деления пополам с адаптивным подбором начального интервала
    #     на основе численной производной для функции f(x1 + alpha * d1, x2 + alpha * d2) Возвращает оптимальное значение alpha
    # direction: Направление поиска (кортеж d1, d2)
    # eps: Точность поиска
    d1, d2 = direction

    def f_alpha(alpha):
        return func(x1 + alpha * d1, x2 + alpha * d2)

    def derivative(alpha):
        return (f_alpha(alpha + eps) - f_alpha(alpha - eps)) / (2 * eps)

    # начинаем с a0 = 0
    alpha0 = 0.0
    d0 = derivative(alpha0)
    if abs(d0) < eps:
        return alpha0
    step = 1.0
    # подбираем интервал так, чтобы f'_α меняла знак
    if d0 < 0: # оптимум правее
        a_bracket = alpha0
        b_bracket = alpha0 + step
        while derivative(b_bracket) < 0:
            step *= 2
            b_bracket = alpha0 + step
            if b_bracket > 1e6:
                break
    else: # d0 > 0, оптимум левее
        b_bracket = alpha0
        a_bracket = alpha0 - step
        while derivative(a_bracket) > 0:
            step *= 2
            a_bracket = alpha0 - step
            if a_bracket < -1e6:
                break
    # бисекция уравнения f'_α(α)=0
    a_val, b_val = a_bracket, b_bracket
    while abs(b_val - a_val) > eps:
        mid = (a_val + b_val) / 2.0
        d_mid = derivative(mid)
        if abs(d_mid) < eps:
            return mid
        elif d_mid < 0:
            a_val = mid
        else:
            b_val = mid
    return (a_val + b_val) / 2.0


def powell_method(f, x0, s1, s2, eps=1e-5):
    x = np.array(x0, dtype=float)
    trajectory = [x.copy()]
    for k in range(2):
        x_start = x.copy()  # начало цикла
        # прямоугольные итерации по s1 и s2
        alpha1 = minimum_search(f, x[0], x[1], s1, eps)
        x = x + alpha1 * s1
        trajectory.append(x.copy())

        alpha2 = minimum_search(f, x[0], x[1], s2, eps)
        x = x + alpha2 * s2
        trajectory.append(x.copy())

        # если изменение мало - завершаем цикл
        if np.linalg.norm(x - x_start) < eps:
            break
    trajectory = trajectory[:-1]
    # поиск по диагонали в направлении нового вектора d = x - x_start
    d_new = x - x_start
    alpha_diag = minimum_search(f, x[0], x[1], d_new, eps)
    x = x + alpha_diag * d_new
    trajectory.append(x.copy())
    return x, np.array(trajectory)


# начальные условия и направления
x0 = np.array([-10, -10])
s1 = np.array([1, 0])
s2 = np.array([0, 1])

# вызов метода пауэлла
result, trajectory = powell_method(f, x0, s1, s2)
print("Найденная точка оптимума методом Пауэлла:", result)
print('Значение функции в точке оптимума:', f(result[0], result[1]))

# построение графика
x1_vals = np.linspace(-15, 20, 200)
x2_vals = np.linspace(-15, 20, 200)
X1, X2 = np.meshgrid(x1_vals, x2_vals)
Z = f(X1, X2)

plt.figure(figsize=(10, 8))
plt.contour(X1, X2, Z, levels=50)

# Поиск оптимума с помощью метода BFGS (градиентный метод)
res = minimize(lambda x: (x[0] - a) ** 2 + (x[1] - b) ** 2 + x[0] * x[1], x0, method='BFGS')
# Добавляем найденную точку на график
plt.plot(res.x[0], res.x[1], 'k*', markersize=12, label='Оптимум (minimize)')

plt.plot(trajectory[:, 0], trajectory[:, 1], 'ro-', label='Траектория поиска')
plt.plot(result[0], result[1], 'bo', label='Найденный минимум')
plt.xlabel('x1')
plt.xlim([-11, 20])
plt.ylabel('x2')
plt.ylim([-11, 10])
plt.title('Метод Пауэлла')
plt.legend()
plt.grid()
plt.show()
