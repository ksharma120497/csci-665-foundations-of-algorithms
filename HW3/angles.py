"""
@Author Kapil Sharma ks4643
@Author Dharma Teja ds3519

The purpose of this code is to find the number of right-angled triangles from a given set of coordinates.

Time Complexity: O(N^2logN)
"""


# Method to find the greatest common divisor
def find_gcd(i, j):
    while j != 0:
        i, j = j, i % j  # Using Euclidean algorithm to find GCD
    return i


# Method to perform binary search to find occurrences of a target slope
def binary_search_slope(slope_list, target_slope):
    low, high = 0, len(slope_list) - 1
    total_count = 0

    while low <= high:
        mid = (low + high) // 2
        mid_value = slope_list[mid]
        if mid_value == target_slope:
            total_count += 1
            # Handle possible duplicates to the left of the mid index
            left = mid - 1
            while left >= low and slope_list[left] == target_slope:
                total_count += 1
                left -= 1
            # Handle possible duplicates to the right of the mid index
            right = mid + 1
            while right <= high and slope_list[right] == target_slope:
                total_count += 1
                right += 1
            return total_count
        elif mid_value < target_slope:
            low = mid + 1
        else:
            high = mid - 1
    return 0


# Method to calculate slopes between all pairs of coordinates
def calculate_slopes(coordinates):
    list_of_slopes = [[] for _ in range(len(coordinates))]
    for i, point1 in enumerate(coordinates):
        for point2 in coordinates:
            if point1 != point2:
                # Calculate the differences in coordinates
                diff_y = point1[1] - point2[1]
                diff_x = point2[0] - point1[0]
                # Calculate the greatest common divisor (GCD) to simplify the slope
                gcd = find_gcd(abs(diff_x), abs(diff_y))
                # If the GCD is zero, set slope to (0, 0), else simplify the slope
                slope_tuple = (0, 0) if gcd == 0 else (diff_x // gcd, diff_y // gcd)
                list_of_slopes[i].append(slope_tuple)
        # Sort the list of slopes for each point to enable binary search
        list_of_slopes[i].sort()
    return list_of_slopes


# Main method
def main():
    number_of_points = int(input())
    coordinates = []
    # Input the coordinates
    for _ in range(number_of_points):
        x, y = map(int, input().split())
        coordinates.append((x, y))
    # Calculate slopes between all pairs of coordinates
    list_of_slopes = calculate_slopes(coordinates)
    total_count = 0
    # Iterate through each set of slopes for each point
    for slopes in list_of_slopes:
        # For each slope, find the perpendicular slopes and count occurrences
        for (diff_y, diff_x) in slopes:
            total_count += binary_search_slope(slopes, (diff_x, -diff_y))
            total_count += binary_search_slope(slopes, (-diff_x, diff_y))
    # Divide the total count by 2 since each triangle is counted twice
    print(total_count // 2)


if __name__ == "__main__":
    main()
