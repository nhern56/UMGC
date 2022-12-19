"""lab 3 """

import sys
import matplotlib.image as mpimg
import matplotlib.pyplot as plt


# Constant tuple of all US states
US_STATES = (
    'Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware',
    'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky',
    'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi',
    'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico',
    'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania',
    'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
    'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
)

# Dict of all capitals per state
US_CAPITALS_BY_STATE = {
    'Alabama': 'Montgomery', 'Alaska': 'Juneau', 'Arizona': 'Phoenix', 'Arkansas': 'Little Rock',
    'California': 'Sacramento', 'Colorado': 'Denver', 'Connecticut': 'Hartford',
    'Delaware': 'Dover', 'Florida': 'Tallahassee', 'Georgia': 'Atlanta', 'Hawaii': 'Honolulu',
    'Idaho': 'Boise', 'Illinois': 'Springfield', 'Indiana': 'Indianapolis', 'Iowa': 'Des Monies',
    'Kansas': 'Topeka', 'Kentucky': 'Frankfort', 'Louisiana': 'Baton Rouge', 'Maine': 'Augusta',
    'Maryland': 'Annapolis', 'Massachusetts': 'Boston', 'Michigan': 'Lansing',
    'Minnesota': 'St. Paul', 'Mississippi': 'Jackson', 'Missouri': 'Jefferson City',
    'Montana': 'Helena', 'Nebraska': 'Lincoln', 'Nevada': 'Carson City', 'New Hampshire': 'Concord',
    'New Jersey': 'Trenton', 'New Mexico': 'Santa Fe', 'New York': 'Albany',
    'North Carolina': 'Raleigh', 'North Dakota': 'Bismarck', 'Ohio': 'Columbus',
    'Oklahoma': 'Oklahoma City', 'Oregon': 'Salem', 'Pennsylvania': 'Harrisburg',
    'Rhode Island': 'Providence', 'South Carolina': 'Columbia', 'South Dakota': 'Pierre',
    'Tennessee': 'Nashville', 'Texas': 'Austin', 'Utah': 'Salt Lake City', 'Vermont': 'Montpelier',
    'Virginia': 'Richmond', 'Washington': 'Olympia', 'West Virginia':
    'Charleston', 'Wisconsin': 'Madison', 'Wyoming': 'Cheyenne'
}
# Dict of all populations per state
US_POPULATION_BY_STATE = {
    'Alabama': 4887680, 'Alaska': 735139, 'Arizona': 7158020, 'Arkansas': 3009730,
    'California': 39461600, 'Colorado': 5691290, 'Connecticut': 3571520, 'Delaware': 965479,
    'Florida': 21244300, 'Georgia': 10511100, 'Hawaii': 1420590, 'Idaho': 1750540,
    'Illinois': 12723100, 'Indiana': 6695500, 'Iowa': 3148620, 'Kansas': 2911360,
    'Kentucky': 4461150, 'Louisiana': 4659690, 'Maine': 1339060, 'Maryland': 6035800,
    'Massachusetts': 6882640, 'Michigan': 9984070, 'Minnesota': 5606250, 'Mississippi': 2981020,
    'Missouri': 6121620, 'Montana': 1060660, 'Nebraska': 1925610, 'Nevada': 3027340,
    'New Hampshire': 1353460, 'New Jersey': 8886020, 'New Mexico': 2092740, 'New York': 19530400,
    'North Carolina': 10381600, 'North Dakota': 758080, 'Ohio': 11676300, 'Oklahoma': 3940240,
    'Oregon': 4181890, 'Pennsylvania': 12800900, 'Rhode Island': 1058290, 'South Carolina': 5084160,
    'South Dakota': 878698, 'Tennessee': 6771630, 'Texas': 28628700, 'Utah': 3153550,
    'Vermont': 624358, 'Virginia': 8501290, 'Washington': 7523870, 'West Virginia': 1804290,
    'Wisconsin': 5807410, 'Wyoming': 577601
}
# List of all flowers per state
US_FLOWERS_BY_STATE = {
    'Alabama': 'Camellia', 'Alaska': 'Alpine Forget-me-not', 'Arizona': 'Saguaro Cactus Blossom',
    'Arkansas': 'Apple Blossom', 'California': 'California Poppy',
    'Colorado': 'Rocky Mountain Columbine', 'Connecticut': 'Mountain Laurel',
    'Delaware': 'Peach Blossom', 'Florida': 'Orange Blossom', 'Georgia': 'Cherokee Rose',
    'Hawaii': 'Pua Alo alo', 'Idaho': 'Syringa', 'Illinois': 'Violet',
    'Indiana': 'Peony', 'Iowa': 'Wild Rose', 'Kansas': 'Wild Native Sunflower',
    'Kentucky': 'Goldenrod', 'Louisiana': 'Magnolia', 'Maine': 'White Pine Tassel',
    'Maryland': 'Black-eyed Susan', 'Massachusetts': 'Mayflower', 'Michigan': 'Dwarf Lake Iris',
    'Minnesota': 'Pink & White Lady Slipper', 'Mississippi': 'Magnolia',
    'Missouri': 'White Hawthorn Blossom', 'Montana': 'Bitterroot', 'Nebraska': 'Goldenrod',
    'Nevada': 'Sagebrush', 'New Hampshire': 'Purple Lilac', 'New Jersey': 'Violet',
    'New Mexico': 'Yucca Flower', 'New York': 'Rose', 'North Carolina': 'American Dogwood',
    'North Dakota': 'Wild Prairie Rose', 'Ohio': 'Red Carnation', 'Oklahoma': 'Mistletoe',
    'Oregon': 'Oregon Grape', 'Pennsylvania': 'Mountain Laurel', 'Rhode Island': 'Violet',
    'South Carolina': 'Yellow Jessamine', 'South Dakota': 'American Pasque', 'Tennessee': 'Iris',
    'Texas': 'Bluebonnet', 'Utah': 'Sego Lily', 'Vermont': 'Red Clover',
    'Virginia': 'American Dogwood', 'Washington': 'Coast Rhododendron',
    'West Virginia': 'Rhododendron', 'Wisconsin': 'Wood Violet', 'Wyoming': 'Indian Paintbrush'
}

# Location of the flower image directory
FLOWER_PICS_FILE = '/Users/Nick/Desktop/Images'


def show_menu_get_choice():
    """Display menu"""
    print('1. Display all U.S. States in Alphabetical order along with the '
          'Capital, State Population, and Flower')
    print('2. Search for a specific state and display the appropriate Capital name, '
          'State Population', 'and an image of the associated State Flower.')
    print('3. Provide a Bar graph of the top 5 populated States showing their overall population.')
    print('4. Update the overall state population for a specific state.')
    print('5. Exit the program')


def display_alphabetical():
    """Display states in alphabetical order"""
    sorted_states = list(US_STATES)
    # sort list in place
    sorted_states.sort()
    # For each state in sorted list, display its info
    for state in sorted_states:
        print(f'State: {state}')
        print(f'Capital: {US_CAPITALS_BY_STATE[state]}')
        print(f'Population: {US_POPULATION_BY_STATE[state]}')
        print(f'Flower: {US_FLOWERS_BY_STATE[state]}')
        print('\n')


def state_search():
    """Displays state information and picture of state flower"""
    state_name = str(input('Enter state name you would like to find: '))
    # displays state information
    display_text = f"State: {state_name}\n" \
                   f"Capital: {US_CAPITALS_BY_STATE[state_name]}\n" \
                   f"Population: {US_POPULATION_BY_STATE[state_name]}"
    print(display_text)
    # Retrieve photos from file
    # Display data as image
    _, axis = plt.subplots()
    img = mpimg.imread(f"{FLOWER_PICS_FILE}/{US_FLOWERS_BY_STATE[state_name].replace('','')}.jpeg")
    axis.imshow(img)
    axis.get_xaxis().set_visible(False)
    axis.get_yaxis().set_visible(False)
    # Show image
    plt.show()


def display_most_populated_states():
    """Displays bar graph of most populated states"""
    state_names = sorted(US_POPULATION_BY_STATE.keys(), reverse=True,
                         key=lambda x: US_POPULATION_BY_STATE[x])[:5]
    state_populations = sorted(US_POPULATION_BY_STATE.values(), reverse=True)[:5]
    # state_names(X), state_populations(y)
    plt.bar(state_names, state_populations)
    # displays state names and population numbers on x and y-axis
    plt.ylabel('Population')
    # title of bar graph
    plt.title('Population of top states.')
    plt.show()


def update_state_population():
    """Allows user to update population"""
    state_name = str(input("Enter state name you would like to find: "))
    new_population = input("Enter new population: ")
    # Update population using entered state and population
    US_POPULATION_BY_STATE[state_name] = new_population
    print("Population has been updated to: ", new_population, "\n")


if __name__ == '__main__':
    """Main method to initialize program"""
    while True:
        show_menu_get_choice()
        choice = int(input())
        if choice == 1:
            display_alphabetical()
        if choice == 2:
            state_search()
        if choice == 3:
            display_most_populated_states()
        if choice == 4:
            update_state_population()
        else:
            sys.exit()
