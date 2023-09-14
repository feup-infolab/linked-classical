<div class="relative">
  <span class="text-lg font-semibold">Link External Entity</span>
  <button
    class="collapse_button absolute top-0 right-0 transition-transform duration-400 transform hover:text-gray-dark">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>

  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <div class="flex-row">
      <div class="my-2 flex flex-row items-center">
        <div class="mr-4">URI</div>
        <input type="text" id="external-entity-uri" class="w-full font-thin border-b p-2"
               placeholder="External Entity URI" />
      </div>
      <div class="my-2 flex flex-row items-center">
        <div class="mr-4">Datasource</div>
        <select name="datasource" id="datasource-selector" class="w-full font-thin border-b p-2">
          <option disabled selected value=""> -- select a datasource -- </option>
        </select>
      </div>
    </div>
    <span id="external-entity-error-message" class="my-8" style="color: red"></span>
    <span id="external-entity-success-message" class="my-8" style="color: green"></span>
    <div class=" text-sm my-4 flex">
      <button id="create-link-button"
              class="my-2 mr-5 py-2 px-2 text-center rounded border bg-gray-dark text-white border-gray-dark">
        Create Link
      </button>
    </div>

  </div>
</div>
