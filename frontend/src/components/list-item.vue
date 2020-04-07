<template>
  <!-- List-item template -->
  <li class="list-item" v-if="item.content">
    <div class="list-item-div" v-show="(edit != true)">
      <input type="checkbox" class="todo-item" :checked="item.completed" @change="toggleValue" />
      <p @dblclick="enableEdit" class="list-label">{{ item.content }}</p>
      <button class="remove-button" @click="removeItem"></button>
    </div>
    <input
      v-show="edit"
      type="text"
      :value="item.content"
      class="edit-input"
      v-on:blur="loseFocus"
      @keypress.enter="leaveInput"
    />
  </li>
</template>

<script>
export default {
  props: {
    item: {
      type: Object
    }
  },
  data() {
    return {
      edit: false
    };
  },
  methods: {
    // Toggles the value of a single item
    toggleValue: function() {
      this.item.completed = !this.item.completed;
      this.$emit("change");
    },
    // when edit-input loses focus
    loseFocus() {
      this.item.content = event.target.value.trim();
      this.edit = false;
      if (this.item.content == "") {
        this.removeItem();
      } else {
        this.$emit("change");
      }
    },
    // press enter when inside edit-input
    leaveInput(event) {
      event.target.blur();
    },
    enableEdit() {
      this.edit = true;
      setTimeout(() => {
        let input = this.$el.querySelector(".edit-input");
        input.focus();
        input.selectionStart = input.value.length; // Flyttar textmarkören till slutet av texten
      }, 50);
    },
    removeItem() {
      this.$emit("remove");
    }
  }
};
</script>

<style>
.list-item-div {
  width: 100%;
  display: flex;
  justify-content: left;
  align-content: center;
  align-items: center;
}
.list-item {
  position: relative;
}
.list-item:hover .remove-button {
  display: block;
}
.edit-input {
  display: flex;
  position: relative;
  box-sizing: border-box;
  width: calc(100% - 45px);
  padding: 16px 14px;
  margin-left: 45px;
  margin-bottom: 1px;
  font: inherit;
  color: inherit;
  border: #999 1px solid;
  box-shadow: inset 0 0 5px 0 rgba(0, 0, 0, 0.2);
}
input[type="checkbox"] + .list-label {
  background-image: url(data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E);
  background-repeat: no-repeat;
  background-position: left;
}
input[type="checkbox"]:checked + .list-label {
  background-image: url(data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23bddad5%22%20stroke-width%3D%223%22/%3E%3Cpath%20fill%3D%22%235dc2af%22%20d%3D%22M72%2025L42%2071%2027%2056l-4%204%2020%2020%2034-52z%22/%3E%3C/svg%3E);
  color: #d9d9d9;
  text-decoration: line-through;
}
.list-label {
  margin: 0;
  width: 100%;
  display: block;
  padding: 15px 15px 15px 60px;
  line-height: 1.2;
  word-break: break-all;
}
.remove-button::after {
  content: "×";
}
.remove-button {
  display: none;
  font-size: 30px;
  transition: color 0.2s ease-out;
  position: absolute;
  right: 10px;
  bottom: 10px;
  padding: 0;
  width: 40px;
  height: 40px;
  color: #cc9a9a;
}
.remove-button:hover {
  color: #af5b5e;
}
.remove-button:active {
  border: none;
}
</style>