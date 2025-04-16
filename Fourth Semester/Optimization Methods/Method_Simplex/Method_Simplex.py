import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import minimize # для нахождения оптимума

a, b = 9, 5
def f(x1, x2, a=9, b=5):
    # Целевая функция: f(x1, x2) = (x1 - 5)^2 + (x2 - 9)^2 + x1*x2
    return (x1 - a) ** 2 + (x2 - b) ** 2 + x1 * x2


# симплексный поиск с равнобедренными треугольниками и условием полного оборота
def simplex_search_isosceles(x0, beta1, beta2, eps=1e-5, max_iter=100, stable_threshold=6):
    # инициализируем симплекс
    x0 = np.array(x0, dtype=float)
    x1 = np.array([x0[0] + beta1, x0[1] + beta2], dtype=float)
    x2 = np.array([x0[0] + beta2, x0[1] + beta1], dtype=float)
    simplex = [x0, x1, x2]

    f0, f1, f2 = f(x0[0], x0[1]), f(x1[0], x1[1]), f(x2[0], x2[1])
    print("Начальный симплекс:")
    print(f"  x0 = {x0}, f(x0) = {f0:.5f}")
    print(f"  x1 = {x1}, f(x1) = {f1:.5f}")
    print(f"  x2 = {x2}, f(x2) = {f2:.5f}")
    print(f"  Расстояния: |x0 -> x1| = {np.linalg.norm(x1 - x0):.5f}, |x0 -> x2| = {np.linalg.norm(x2 - x0):.5f}\n")

    C_search = [0, 0]

    simplex_history = [[pt.copy() for pt in simplex]]
    stable_count = 0
    last_best = None
    iteration = 0

    while iteration < max_iter:
        # Сортировка вершин по значению функции: A (лучший), B (второй), C (худший)
        f_vals = [f(pt[0], pt[1]) for pt in simplex]
        indices = np.argsort(f_vals)
        simplex = [simplex[i] for i in indices]
        # f_vals = [f_vals[i] for i in indices]
        A, B, C = simplex

        print(f"Итерация {iteration}:")
        print(f"  A = {A}, f(A) = {f(A[0], A[1]):.5f}")
        print(f"  B = {B}, f(B) = {f(B[0], B[1]):.5f}")
        print(f"  C = {C}, f(C) = {f(C[0], C[1]):.5f}")

        # проверка: если лучшая точка не изменилась, увеличиваем счетчик стабильности
        if last_best is not None and np.allclose(A, last_best, atol=eps):
            stable_count += 1
        else:
            stable_count = 1  # начинаем новый отсчет
            last_best = A.copy()
        print(f"  Счетчик стабильности (A не меняется): {stable_count} (порог: {stable_threshold})")

        # если лучшая вершина не изменилась в течение stable_threshold итераций, завершаем поиск
        if stable_count >= stable_threshold:
            print("Лучшая вершина не менялась в течение 6 итераций – полный круг завершен, оптимум найден.\n")
            print('Фактическая точность:', np.linalg.norm(B - A))
            break

        # проверка сходимости по расстоянию между двумя лучшими точками
        if np.linalg.norm(B - A) < eps:
            print('Фактическая точность:', np.linalg.norm(B - A))
            print("Расстояние между двумя лучшими точками меньше эпсилона. Завершаем оптимизацию.\n")
            break

        if C[0] == C_search[0] and C[1] == C_search[1]:
            M = (A + C) / 2.0
            # Отражаем худшую вершину относительно M
            C_new = 2 * M - B
            C_search = C_new
            simplex = [A, C, C_new]
        else:
            # Вычисляем середину основания (две лучшие точки)
            M = (A + B) / 2.0
            # Отражаем худшую вершину относительно M
            C_new = 2 * M - C
            C_search = C_new
            simplex = [A, B, C_new]
        print(f"  Середина основания (M) = {M}")
        print(f"  Отраженная точка C_new = {C_new}, f(C_new) = {f(C_new[0], C_new[1]):.5f}\n")

        # Обновляем симплекс: сохраняем A и B, заменяя C на C_new
        simplex_history.append([pt.copy() for pt in simplex])
        iteration += 1

    # Финальная сортировка симплекса
    f_vals = [f(pt[0], pt[1]) for pt in simplex]
    indices = np.argsort(f_vals)
    simplex = [simplex[i] for i in indices]
    best_point = simplex[0]
    best_value = f(best_point[0], best_point[1])
    print(f"Оптимизация завершена. Лучшая точка: {best_point}, f = {best_value:.5f}")
    return best_point, best_value, simplex_history


if __name__ == "__main__":
    x0 = [-10, -10]
    N = 2
    alpha1 = 6
    alpha2 = 2

    a1_beta1 = ((N + 1) ** 0.5 + N - 1) * alpha1 / (N * 2 ** 0.5)
    a1_beta2 = ((N + 1) ** 0.5 - 1) * alpha1 / (N * 2 ** 0.5)

    a2_beta1 = ((N + 1) ** 0.5 + N - 1) * alpha2 / (N * 2 ** 0.5)
    a2_beta2 = ((N + 1) ** 0.5 - 1) * alpha2 / (N * 2 ** 0.5)

    best_point_1, best_value_1, simplex_history_1 = simplex_search_isosceles(x0, a1_beta1, a1_beta2)
    best_point_2, best_value_2, simplex_history_2 = simplex_search_isosceles(best_point_1, a2_beta1, a2_beta2)

    x_range = np.linspace(-10, 20)
    y_range = np.linspace(-10, 20)
    # x_range = np.linspace(7, 10)
    # y_range = np.linspace(0, 5)
    X, Y = np.meshgrid(x_range, y_range)
    Z = f(X, Y)

    plt.figure(figsize=(10, 8))
    contour = plt.contour(X, Y, Z, levels=50)
    plt.clabel(contour, fontsize=8)

    for simplex in simplex_history_1:
        pts = np.array(simplex)
        pts = np.vstack((pts, pts[0]))
        plt.plot(pts[:, 0], pts[:, 1], 'b--',
                 label='Симплекс (alpha=6)' if 'Симплекс (alpha=6)' not in plt.gca().get_legend_handles_labels()[
                     1] else "")

    for simplex in simplex_history_2:
        pts = np.array(simplex)
        pts = np.vstack((pts, pts[0]))
        plt.plot(pts[:, 0], pts[:, 1], 'g--',
                 label='Симплекс (alpha=2)' if 'Симплекс (alpha=2)' not in plt.gca().get_legend_handles_labels()[
                     1] else "")

    # Поиск оптимума с помощью метода BFGS (градиентный метод)
    res = minimize(lambda x: (x[0] - a) ** 2 + (x[1] - b) ** 2 + x[0] * x[1], x0, method='BFGS')
    # Добавляем найденную точку на график
    plt.plot(res.x[0], res.x[1], 'k*', markersize=12, label='Оптимум (minimize)')

    plt.plot(best_point_1[0], best_point_1[1], 'ro', label='Оптимум (alpha=6)')
    plt.plot(best_point_2[0], best_point_2[1], 'ko', label='Оптимум (alpha=2)')

    plt.xlabel('x1')
    plt.ylabel('x2')
    plt.title('Симплексный поиск для alpha=6 и alpha=2')
    plt.legend()
    plt.grid(True)
    plt.show()

