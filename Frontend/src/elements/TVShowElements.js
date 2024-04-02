const updateProperties = (elem, state) => {

    elem.style.setProperty('--x', `${ state.x }px`)
    elem.style.setProperty('--y', `${ state.y }px`)
    elem.style.setProperty('--width', `${ state.width }px`)
    elem.style.setProperty('--height', `${ state.height }px`)
    elem.style.setProperty('--radius', state.radius)
    elem.style.setProperty('--scale', state.scale)

}

document.querySelectorAll('.cursor').forEach((cursor) => {

    let onElement

    const createState = (e) => {

        const defaultState = {
            x: e.clientX,
            y: e.clientY,
            width: 42,
            height: 42,
            radius: '100px'
        }

        const computedState = {}

        if (onElement != null) {
            const { top, left, width, height } = onElement.getBoundingClientRect()
            const radius = window.getComputedStyle(onElement).borderTopLeftRadius

            computedState.x = left + width / 2
            computedState.y = top + height / 2
            computedState.width = width
            computedState.height = height
            computedState.radius = radius
        }

        return {
            ...defaultState,
            ...computedState
        }

    }

    document.addEventListener('mousemove', (e) => {
        const state = createState(e)
        updateProperties(cursor, state)
    })

    document.body.addEventListener('mouseover', (e) => {
        const targetElement = e.target.closest('a, .btn, .btn-block button, .btn-wait, .main-button');
        if (targetElement) {
            onElement = targetElement;
        }
    });

    document.body.addEventListener('mouseout', (e) => {
        const targetElement = e.target.closest('a, .btn, .btn-block button, .btn-wait, .main-button');
        if (targetElement === onElement) {
            onElement = undefined;
        }
    });


})

const root = document.documentElement;
const dropdownTitle = document.querySelector(".dropdown-title");
const dropdownList = document.querySelector(".dropdown-list");
const mainButton = document.querySelector(".main-button");

const listItems = ["Season 1", "Season 2", "Season 3", "Season 4", "Season 5"];

const listItemTemplate = (text, translateValue) => {
    return `
    <li class="dropdown-list-item">
      <button class="dropdown-button list-button" data-translate-value="${translateValue}%">
        <span class="text-truncate">${text}</span>
      </button>
    </li>
  `;
};

const renderListItems = () => {
    dropdownList.innerHTML += listItems
        .map((item, index) => {
        return listItemTemplate(item, 100 * index);
    })
        .join("");
};

window.addEventListener("load", () => {
    renderListItems();
});

const setDropdownProps = (ht, opacity) => {
    root.style.setProperty("--dropdown-height", ht !== 0 ? ht + "rem" : 0);
    root.style.setProperty("--list-opacity", opacity);
};

mainButton.addEventListener("click", () => {
    const listWrapperSizes = 3.5; // margins, paddings & borders
    const dropdownOpenHeight = 4.6 * listItems.length + listWrapperSizes;
    const currDropdownHeight =
    root.style.getPropertyValue("--dropdown-height") || "0";

    currDropdownHeight === "0"
    ? setDropdownProps(dropdownOpenHeight, 1)
    : setDropdownProps(0, 0);
});

dropdownList.addEventListener("mouseover", (e) => {
    const translateValue = e.target.dataset.translateValue;
    root.style.setProperty("--translate-value", translateValue);
});

dropdownList.addEventListener("click", (e) => {
    const clickedItemText = e.target.innerText.toLowerCase().trim();
    dropdownTitle.innerHTML = clickedItemText;
    setDropdownProps(0, 0);
});
