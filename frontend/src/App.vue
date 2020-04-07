<template>
    <div id="list-section">
        <header id="list-header">
            <div id="toggle-all-id" v-show="listItems.length > 0">
                <input
                        type="checkbox"
                        id="check-all"
                        class="check-all-class"
                        :checked="allChecked"
                        @change="toggleAll"
                />
                <label class="check-all-class" for="check-all"></label>
            </div>
            <input
                    id="todo-input"
                    placeholder="What needs to be done?"
                    @keypress.enter="newInput"
            />
        </header>
        <main id="main-section">
            <ul>
                <list-item
                        v-for="(item, index) in filteredItems"
                        :item="item"
                        :key="index"
                        @remove="removeItem(item)"
                        @change="saveToLocalStorage"
                ></list-item>
            </ul>
        </main>
        <footer id="list-footer" v-show="listItems.length > 0">
            <span id="todos-count">{{ itemsLeft }}</span>
            <div id="filter-list">
                <input
                        type="radio"
                        name="filter"
                        id="filter-all"
                        value
                        :checked="filter == null"
                        @click="setHash"
                />
                <label for="filter-all">All</label>
                <input
                        type="radio"
                        name="filter"
                        id="filter-active"
                        value="#/active"
                        :checked="filter == 0"
                        @click="setHash"
                />
                <label for="filter-active">Active</label>
                <input
                        type="radio"
                        name="filter"
                        id="filter-completed"
                        value="#/completed"
                        :checked="filter == 1"
                        @click="setHash"
                />
                <label for="filter-completed">Completed</label>
            </div>
            <button
                    v-show="hasCompleted"
                    id="completed-button"
                    @click="clearCompleted"
            >
                Clear completed
            </button>
        </footer>
    </div>
</template>

<script>
    import listItemComponent from "./components/list-item";

    export default {
        name: "App",
        data() {
            return {
                listItems: [], // Contains taskId, order, content, completed, listId
                filter: null
            };
        },
        components: {
            "list-item": listItemComponent
        },
        methods: {
            // Load data from Local Storage
            loadSavedData() {
                let localStorageObject = Object.entries(localStorage).filter(
                    k => k[0].indexOf("todos") != -1
                ); // Funkar inte i Edge

                // Sort based on "local storage"-key, all keys are unique, form of "todos-X"
                const indexOfDash = "todos-".length;
                localStorageObject.sort((e1, e2) => {
                    // Sort the string numerically, not alphabetically
                    if (
                        Number(e1[0].substr(indexOfDash)) < Number(e2[0].substr(indexOfDash))
                    )
                        return -1;
                    else return 1;
                });
                localStorageObject.forEach(entry =>
                    this.listItems.push(JSON.parse(entry[1]))
                );
            },
            // Load data from API
            loadSavedDataAPI: async function () {
                let request = await fetch("/api/tasks/get");
                if (request.ok) {
                    let response = await request.json();
                    this.listItems = response;
                    this.listItems.sort((a, b) => a.order < b.order)
                } else
                    console.log(await request.text())
            },
            // keyPress "enter" event from main input-box
            newInput(event) {
                var todoInput = event.target;
                if (todoInput.value.trim().length > 0) {
                    this.newItem(todoInput.value);
                    todoInput.value = null;
                }
            },
            // Removes specific item in list, save to localStorage and re-populate
            removeItem(itemToRemove) {
                this.listItems = this.listItems.filter(node => node != itemToRemove);
                this.saveToLocalStorage();
            },
            // Saves items to localStorage as stringified JSON-objects
            saveToLocalStorage() {
                window.localStorage.clear();
                for (let i = 0; i < this.listItems.length; i++) {
                    window.localStorage.setItem(
                        "todos-" + i,
                        JSON.stringify(this.listItems[i])
                    );
                }
            },
            // Create new item, save it to localStorage and re-populate list
            newItem(content) {
                const newItem = {content: content.trim(), value: false};
                this.listItems.push(newItem);
                this.saveToLocalStorage();
            },
            // set locationHash to filter
            setHash(event) {
                let filterSelected = event.target;
                location.hash = filterSelected.value;
                this.setFilter();
            },
            // set active filter
            setFilter() {
                if (location.hash === "#/active") {
                    this.filter = false;
                } else if (location.hash === "#/completed") {
                    this.filter = true;
                } else {
                    this.filter = null;
                }
            },
            // Removes completed items from list
            clearCompleted() {
                this.listItems = this.listItems.filter(todo => todo.value == false);
                this.saveToLocalStorage();
            },
            // Toggle all items either on or off
            toggleAll() {
                let bool = !this.allChecked;
                this.listItems.forEach(todo => {
                    todo.value = bool;
                });
                this.saveToLocalStorage();
            }
        },
        computed: {
            filteredItems() {
                return this.listItems.filter(item => this.filter == null || item.value == this.filter);
            },
            // "X items left"-text
            itemsLeft() {
                return (
                    this.listItems.length +
                    (this.listItems.length == 1 ? " item" : " items") +
                    " left"
                );
            },
            hasCompleted() {
                return this.listItems.filter(todo => todo.value == true).length > 0;
            },
            // Used to toggle "check all"-arrow if all items are already checked
            allChecked() {
                if (this.listItems.length == 0) {
                    return false;
                }

                return (
                    this.listItems.filter(todo => todo.value == true).length ==
                    this.listItems.length
                );
            }
        },
        created() {
            // Read from local storage
            // this.loadSavedData();

            // Read from API
            this.loadSavedDataAPI();

            // Set filter on load
            this.setFilter();
        }
    };
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
