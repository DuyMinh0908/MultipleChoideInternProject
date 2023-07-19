import { defineStore } from "pinia";

type CountState = {
  count: number;
};
export const useCount = defineStore("count", {
  state: (): CountState => ({
    count: 0,
  }),
  getters: {
    getCount: (state: CountState) => {
      return state.count;
    },
  },
  actions: {
    setCount: function () {
      this.count++;
      console.log(this.count);
    },
  },
});
