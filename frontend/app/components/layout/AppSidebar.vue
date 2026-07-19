<template>
  <aside class="flex h-screen w-52 shrink-0 flex-col border-r border-slate-300 bg-white">
    <div class="flex h-14 items-center gap-2 border-b border-slate-300 px-4">
      <div class="flex h-6 w-6 items-center justify-center border border-sky-700 text-xs font-bold text-sky-800">
        W
      </div>
      <span class="text-sm font-semibold tracking-widest text-slate-900">
        WORKHUB
      </span>
    </div>

    <nav class="flex flex-1 flex-col gap-1 py-3 text-sm">
      <NuxtLink
        to="/dashboard"
        class="border-l-2 border-sky-700 bg-sky-50 px-4 py-2 font-semibold text-sky-900"
      >
        ダッシュボード
      </NuxtLink>

      <NuxtLink
        to="/dashboard"
        class="border-l-2 border-transparent px-4 py-2 text-slate-700 hover:bg-slate-100"
      >
        案件
      </NuxtLink>
    </nav>
  </aside>
</template>